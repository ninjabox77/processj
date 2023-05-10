// Generated from /Users/oswaldocisneros/Documents/compiler/pj/src/antlr/ProcessJ.g4 by ANTLR 4.12.0
package parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ProcessJParser}.
 */
public interface ProcessJListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#init}.
	 * @param ctx the parse tree
	 */
	void enterInit(ProcessJParser.InitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#init}.
	 * @param ctx the parse tree
	 */
	void exitInit(ProcessJParser.InitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterPackageDeclaration(ProcessJParser.PackageDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitPackageDeclaration(ProcessJParser.PackageDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SingleImportDeclaration_}
	 * labeled alternative in {@link ProcessJParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSingleImportDeclaration_(ProcessJParser.SingleImportDeclaration_Context ctx);
	/**
	 * Exit a parse tree produced by the {@code SingleImportDeclaration_}
	 * labeled alternative in {@link ProcessJParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSingleImportDeclaration_(ProcessJParser.SingleImportDeclaration_Context ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiImportDeclaration_}
	 * labeled alternative in {@link ProcessJParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMultiImportDeclaration_(ProcessJParser.MultiImportDeclaration_Context ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiImportDeclaration_}
	 * labeled alternative in {@link ProcessJParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMultiImportDeclaration_(ProcessJParser.MultiImportDeclaration_Context ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiImportDeclarationStar_}
	 * labeled alternative in {@link ProcessJParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMultiImportDeclarationStar_(ProcessJParser.MultiImportDeclarationStar_Context ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiImportDeclarationStar_}
	 * labeled alternative in {@link ProcessJParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMultiImportDeclarationStar_(ProcessJParser.MultiImportDeclarationStar_Context ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#singleImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSingleImportDeclaration(ProcessJParser.SingleImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#singleImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSingleImportDeclaration(ProcessJParser.SingleImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#multiImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMultiImportDeclaration(ProcessJParser.MultiImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#multiImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMultiImportDeclaration(ProcessJParser.MultiImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#multiImportDeclarationStar}.
	 * @param ctx the parse tree
	 */
	void enterMultiImportDeclarationStar(ProcessJParser.MultiImportDeclarationStarContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#multiImportDeclarationStar}.
	 * @param ctx the parse tree
	 */
	void exitMultiImportDeclarationStar(ProcessJParser.MultiImportDeclarationStarContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclaration(ProcessJParser.TypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclaration(ProcessJParser.TypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterProcedureDeclaration(ProcessJParser.ProcedureDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitProcedureDeclaration(ProcessJParser.ProcedureDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#modifier}.
	 * @param ctx the parse tree
	 */
	void enterModifier(ProcessJParser.ModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#modifier}.
	 * @param ctx the parse tree
	 */
	void exitModifier(ProcessJParser.ModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#recordDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterRecordDeclaration(ProcessJParser.RecordDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#recordDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitRecordDeclaration(ProcessJParser.RecordDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#extends}.
	 * @param ctx the parse tree
	 */
	void enterExtends(ProcessJParser.ExtendsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#extends}.
	 * @param ctx the parse tree
	 */
	void exitExtends(ProcessJParser.ExtendsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#recordBody}.
	 * @param ctx the parse tree
	 */
	void enterRecordBody(ProcessJParser.RecordBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#recordBody}.
	 * @param ctx the parse tree
	 */
	void exitRecordBody(ProcessJParser.RecordBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#recordField}.
	 * @param ctx the parse tree
	 */
	void enterRecordField(ProcessJParser.RecordFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#recordField}.
	 * @param ctx the parse tree
	 */
	void exitRecordField(ProcessJParser.RecordFieldContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ProtocolDeclarationWithBody}
	 * labeled alternative in {@link ProcessJParser#protocolDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterProtocolDeclarationWithBody(ProcessJParser.ProtocolDeclarationWithBodyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ProtocolDeclarationWithBody}
	 * labeled alternative in {@link ProcessJParser#protocolDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitProtocolDeclarationWithBody(ProcessJParser.ProtocolDeclarationWithBodyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ProtocolDeclarationSemi}
	 * labeled alternative in {@link ProcessJParser#protocolDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterProtocolDeclarationSemi(ProcessJParser.ProtocolDeclarationSemiContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ProtocolDeclarationSemi}
	 * labeled alternative in {@link ProcessJParser#protocolDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitProtocolDeclarationSemi(ProcessJParser.ProtocolDeclarationSemiContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#protocolBody}.
	 * @param ctx the parse tree
	 */
	void enterProtocolBody(ProcessJParser.ProtocolBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#protocolBody}.
	 * @param ctx the parse tree
	 */
	void exitProtocolBody(ProcessJParser.ProtocolBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#protocolCase}.
	 * @param ctx the parse tree
	 */
	void enterProtocolCase(ProcessJParser.ProtocolCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#protocolCase}.
	 * @param ctx the parse tree
	 */
	void exitProtocolCase(ProcessJParser.ProtocolCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#constantDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstantDeclaration(ProcessJParser.ConstantDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#constantDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstantDeclaration(ProcessJParser.ConstantDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#packageAccess}.
	 * @param ctx the parse tree
	 */
	void enterPackageAccess(ProcessJParser.PackageAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#packageAccess}.
	 * @param ctx the parse tree
	 */
	void exitPackageAccess(ProcessJParser.PackageAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(ProcessJParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(ProcessJParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableDeclarationStatement(ProcessJParser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableDeclarationStatement(ProcessJParser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableDeclaration(ProcessJParser.LocalVariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableDeclaration(ProcessJParser.LocalVariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#variableDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorList(ProcessJParser.VariableDeclaratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#variableDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorList(ProcessJParser.VariableDeclaratorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarator(ProcessJParser.VariableDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarator(ProcessJParser.VariableDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#variableDeclaratorIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorIdentifier(ProcessJParser.VariableDeclaratorIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#variableDeclaratorIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorIdentifier(ProcessJParser.VariableDeclaratorIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#typeType}.
	 * @param ctx the parse tree
	 */
	void enterTypeType(ProcessJParser.TypeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#typeType}.
	 * @param ctx the parse tree
	 */
	void exitTypeType(ProcessJParser.TypeTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(ProcessJParser.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(ProcessJParser.PrimitiveTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#referenceType}.
	 * @param ctx the parse tree
	 */
	void enterReferenceType(ProcessJParser.ReferenceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#referenceType}.
	 * @param ctx the parse tree
	 */
	void exitReferenceType(ProcessJParser.ReferenceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#numericType}.
	 * @param ctx the parse tree
	 */
	void enterNumericType(ProcessJParser.NumericTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#numericType}.
	 * @param ctx the parse tree
	 */
	void exitNumericType(ProcessJParser.NumericTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#integralType}.
	 * @param ctx the parse tree
	 */
	void enterIntegralType(ProcessJParser.IntegralTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#integralType}.
	 * @param ctx the parse tree
	 */
	void exitIntegralType(ProcessJParser.IntegralTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#floatingPointType}.
	 * @param ctx the parse tree
	 */
	void enterFloatingPointType(ProcessJParser.FloatingPointTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#floatingPointType}.
	 * @param ctx the parse tree
	 */
	void exitFloatingPointType(ProcessJParser.FloatingPointTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(ProcessJParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(ProcessJParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ChannelSharedReadType}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void enterChannelSharedReadType(ProcessJParser.ChannelSharedReadTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ChannelSharedReadType}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void exitChannelSharedReadType(ProcessJParser.ChannelSharedReadTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ChannelSharedWriteType}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void enterChannelSharedWriteType(ProcessJParser.ChannelSharedWriteTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ChannelSharedWriteType}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void exitChannelSharedWriteType(ProcessJParser.ChannelSharedWriteTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ChannelSharedType}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void enterChannelSharedType(ProcessJParser.ChannelSharedTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ChannelSharedType}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void exitChannelSharedType(ProcessJParser.ChannelSharedTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ChannelType_}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void enterChannelType_(ProcessJParser.ChannelType_Context ctx);
	/**
	 * Exit a parse tree produced by the {@code ChannelType_}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void exitChannelType_(ProcessJParser.ChannelType_Context ctx);
	/**
	 * Enter a parse tree produced by the {@code ChannelDotRead}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void enterChannelDotRead(ProcessJParser.ChannelDotReadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ChannelDotRead}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void exitChannelDotRead(ProcessJParser.ChannelDotReadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ChannelDotWrite}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void enterChannelDotWrite(ProcessJParser.ChannelDotWriteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ChannelDotWrite}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void exitChannelDotWrite(ProcessJParser.ChannelDotWriteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ChannelSharedDotRead}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void enterChannelSharedDotRead(ProcessJParser.ChannelSharedDotReadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ChannelSharedDotRead}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void exitChannelSharedDotRead(ProcessJParser.ChannelSharedDotReadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ChannelSharedDotWrite}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void enterChannelSharedDotWrite(ProcessJParser.ChannelSharedDotWriteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ChannelSharedDotWrite}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void exitChannelSharedDotWrite(ProcessJParser.ChannelSharedDotWriteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeVariableIdentifier}
	 * labeled alternative in {@link ProcessJParser#typeVariable}.
	 * @param ctx the parse tree
	 */
	void enterTypeVariableIdentifier(ProcessJParser.TypeVariableIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeVariableIdentifier}
	 * labeled alternative in {@link ProcessJParser#typeVariable}.
	 * @param ctx the parse tree
	 */
	void exitTypeVariableIdentifier(ProcessJParser.TypeVariableIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeVariablePackageDdotIdentifier}
	 * labeled alternative in {@link ProcessJParser#typeVariable}.
	 * @param ctx the parse tree
	 */
	void enterTypeVariablePackageDdotIdentifier(ProcessJParser.TypeVariablePackageDdotIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeVariablePackageDdotIdentifier}
	 * labeled alternative in {@link ProcessJParser#typeVariable}.
	 * @param ctx the parse tree
	 */
	void exitTypeVariablePackageDdotIdentifier(ProcessJParser.TypeVariablePackageDdotIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeVariablePackageDotIdentifier}
	 * labeled alternative in {@link ProcessJParser#typeVariable}.
	 * @param ctx the parse tree
	 */
	void enterTypeVariablePackageDotIdentifier(ProcessJParser.TypeVariablePackageDotIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeVariablePackageDotIdentifier}
	 * labeled alternative in {@link ProcessJParser#typeVariable}.
	 * @param ctx the parse tree
	 */
	void exitTypeVariablePackageDotIdentifier(ProcessJParser.TypeVariablePackageDotIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#dims}.
	 * @param ctx the parse tree
	 */
	void enterDims(ProcessJParser.DimsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#dims}.
	 * @param ctx the parse tree
	 */
	void exitDims(ProcessJParser.DimsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void enterVariableInitializer(ProcessJParser.VariableInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void exitVariableInitializer(ProcessJParser.VariableInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(ProcessJParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(ProcessJParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TernaryExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTernaryExpression(ProcessJParser.TernaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TernaryExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTernaryExpression(ProcessJParser.TernaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RecordLiteralExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRecordLiteralExpression(ProcessJParser.RecordLiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RecordLiteralExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRecordLiteralExpression(ProcessJParser.RecordLiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionWithBlockExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionWithBlockExpression(ProcessJParser.ExpressionWithBlockExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionWithBlockExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionWithBlockExpression(ProcessJParser.ExpressionWithBlockExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PathExpression_}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPathExpression_(ProcessJParser.PathExpression_Context ctx);
	/**
	 * Exit a parse tree produced by the {@code PathExpression_}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPathExpression_(ProcessJParser.PathExpression_Context ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterReturnExpression(ProcessJParser.ReturnExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitReturnExpression(ProcessJParser.ReturnExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReadWriteExpressionCall}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterReadWriteExpressionCall(ProcessJParser.ReadWriteExpressionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReadWriteExpressionCall}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitReadWriteExpressionCall(ProcessJParser.ReadWriteExpressionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AndExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(ProcessJParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AndExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(ProcessJParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReadWriteExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterReadWriteExpression(ProcessJParser.ReadWriteExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReadWriteExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitReadWriteExpression(ProcessJParser.ReadWriteExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ContinueExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterContinueExpression(ProcessJParser.ContinueExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ContinueExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitContinueExpression(ProcessJParser.ContinueExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SuspendExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSuspendExpression(ProcessJParser.SuspendExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SuspendExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSuspendExpression(ProcessJParser.SuspendExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignmentExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(ProcessJParser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignmentExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(ProcessJParser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MethodCallExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMethodCallExpression(ProcessJParser.MethodCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MethodCallExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMethodCallExpression(ProcessJParser.MethodCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TimeoutExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTimeoutExpression(ProcessJParser.TimeoutExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TimeoutExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTimeoutExpression(ProcessJParser.TimeoutExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LiteralExpression_}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpression_(ProcessJParser.LiteralExpression_Context ctx);
	/**
	 * Exit a parse tree produced by the {@code LiteralExpression_}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpression_(ProcessJParser.LiteralExpression_Context ctx);
	/**
	 * Enter a parse tree produced by the {@code ProtocolLiteralExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterProtocolLiteralExpression(ProcessJParser.ProtocolLiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ProtocolLiteralExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitProtocolLiteralExpression(ProcessJParser.ProtocolLiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConditionalAndExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalAndExpression(ProcessJParser.ConditionalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConditionalAndExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalAndExpression(ProcessJParser.ConditionalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SyncExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSyncExpression(ProcessJParser.SyncExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SyncExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSyncExpression(ProcessJParser.SyncExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NegationExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNegationExpression(ProcessJParser.NegationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NegationExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNegationExpression(ProcessJParser.NegationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiplicativeExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(ProcessJParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiplicativeExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(ProcessJParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CallExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCallExpression(ProcessJParser.CallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CallExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCallExpression(ProcessJParser.CallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ClassExpression_}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterClassExpression_(ProcessJParser.ClassExpression_Context ctx);
	/**
	 * Exit a parse tree produced by the {@code ClassExpression_}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitClassExpression_(ProcessJParser.ClassExpression_Context ctx);
	/**
	 * Enter a parse tree produced by the {@code ExponentExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExponentExpression(ProcessJParser.ExponentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExponentExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExponentExpression(ProcessJParser.ExponentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExternalLiteralExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExternalLiteralExpression(ProcessJParser.ExternalLiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExternalLiteralExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExternalLiteralExpression(ProcessJParser.ExternalLiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AdditiveExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(ProcessJParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AdditiveExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(ProcessJParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RelationalExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(ProcessJParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RelationalExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(ProcessJParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InclusiveOrExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInclusiveOrExpression(ProcessJParser.InclusiveOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InclusiveOrExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInclusiveOrExpression(ProcessJParser.InclusiveOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayLiteralExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayLiteralExpression(ProcessJParser.ArrayLiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayLiteralExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayLiteralExpression(ProcessJParser.ArrayLiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExclusiveOrExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExclusiveOrExpression(ProcessJParser.ExclusiveOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExclusiveOrExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExclusiveOrExpression(ProcessJParser.ExclusiveOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GroupedExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterGroupedExpression(ProcessJParser.GroupedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GroupedExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitGroupedExpression(ProcessJParser.GroupedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ShiftExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterShiftExpression(ProcessJParser.ShiftExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ShiftExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitShiftExpression(ProcessJParser.ShiftExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BreakExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBreakExpression(ProcessJParser.BreakExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BreakExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBreakExpression(ProcessJParser.BreakExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FieldExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFieldExpression(ProcessJParser.FieldExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FieldExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFieldExpression(ProcessJParser.FieldExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SkipStopExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSkipStopExpression(ProcessJParser.SkipStopExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SkipStopExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSkipStopExpression(ProcessJParser.SkipStopExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AnnotationExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationExpression(ProcessJParser.AnnotationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AnnotationExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationExpression(ProcessJParser.AnnotationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PreUnaryExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPreUnaryExpression(ProcessJParser.PreUnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PreUnaryExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPreUnaryExpression(ProcessJParser.PreUnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostUnaryExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPostUnaryExpression(ProcessJParser.PostUnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostUnaryExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPostUnaryExpression(ProcessJParser.PostUnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignmentOperatorExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentOperatorExpression(ProcessJParser.AssignmentOperatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignmentOperatorExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentOperatorExpression(ProcessJParser.AssignmentOperatorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CastExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpression(ProcessJParser.CastExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CastExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpression(ProcessJParser.CastExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayAccessExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccessExpression(ProcessJParser.ArrayAccessExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayAccessExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccessExpression(ProcessJParser.ArrayAccessExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConditionalOrExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalOrExpression(ProcessJParser.ConditionalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConditionalOrExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalOrExpression(ProcessJParser.ConditionalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(ProcessJParser.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(ProcessJParser.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#normalAnnotation}.
	 * @param ctx the parse tree
	 */
	void enterNormalAnnotation(ProcessJParser.NormalAnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#normalAnnotation}.
	 * @param ctx the parse tree
	 */
	void exitNormalAnnotation(ProcessJParser.NormalAnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#elementValuePairList}.
	 * @param ctx the parse tree
	 */
	void enterElementValuePairList(ProcessJParser.ElementValuePairListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#elementValuePairList}.
	 * @param ctx the parse tree
	 */
	void exitElementValuePairList(ProcessJParser.ElementValuePairListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#elementValuePair}.
	 * @param ctx the parse tree
	 */
	void enterElementValuePair(ProcessJParser.ElementValuePairContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#elementValuePair}.
	 * @param ctx the parse tree
	 */
	void exitElementValuePair(ProcessJParser.ElementValuePairContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#elementValue}.
	 * @param ctx the parse tree
	 */
	void enterElementValue(ProcessJParser.ElementValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#elementValue}.
	 * @param ctx the parse tree
	 */
	void exitElementValue(ProcessJParser.ElementValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#markerAnnotation}.
	 * @param ctx the parse tree
	 */
	void enterMarkerAnnotation(ProcessJParser.MarkerAnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#markerAnnotation}.
	 * @param ctx the parse tree
	 */
	void exitMarkerAnnotation(ProcessJParser.MarkerAnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#singleElementAnnotation}.
	 * @param ctx the parse tree
	 */
	void enterSingleElementAnnotation(ProcessJParser.SingleElementAnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#singleElementAnnotation}.
	 * @param ctx the parse tree
	 */
	void exitSingleElementAnnotation(ProcessJParser.SingleElementAnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#literalExpression}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpression(ProcessJParser.LiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#literalExpression}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpression(ProcessJParser.LiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#pathExpression}.
	 * @param ctx the parse tree
	 */
	void enterPathExpression(ProcessJParser.PathExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#pathExpression}.
	 * @param ctx the parse tree
	 */
	void exitPathExpression(ProcessJParser.PathExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#actualDeclarationList}.
	 * @param ctx the parse tree
	 */
	void enterActualDeclarationList(ProcessJParser.ActualDeclarationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#actualDeclarationList}.
	 * @param ctx the parse tree
	 */
	void exitActualDeclarationList(ProcessJParser.ActualDeclarationListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FormalDeclaration_}
	 * labeled alternative in {@link ProcessJParser#formalDeclarationList}.
	 * @param ctx the parse tree
	 */
	void enterFormalDeclaration_(ProcessJParser.FormalDeclaration_Context ctx);
	/**
	 * Exit a parse tree produced by the {@code FormalDeclaration_}
	 * labeled alternative in {@link ProcessJParser#formalDeclarationList}.
	 * @param ctx the parse tree
	 */
	void exitFormalDeclaration_(ProcessJParser.FormalDeclaration_Context ctx);
	/**
	 * Enter a parse tree produced by the {@code LastFormalDeclaration_}
	 * labeled alternative in {@link ProcessJParser#formalDeclarationList}.
	 * @param ctx the parse tree
	 */
	void enterLastFormalDeclaration_(ProcessJParser.LastFormalDeclaration_Context ctx);
	/**
	 * Exit a parse tree produced by the {@code LastFormalDeclaration_}
	 * labeled alternative in {@link ProcessJParser#formalDeclarationList}.
	 * @param ctx the parse tree
	 */
	void exitLastFormalDeclaration_(ProcessJParser.LastFormalDeclaration_Context ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#formalDeclarations}.
	 * @param ctx the parse tree
	 */
	void enterFormalDeclarations(ProcessJParser.FormalDeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#formalDeclarations}.
	 * @param ctx the parse tree
	 */
	void exitFormalDeclarations(ProcessJParser.FormalDeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#formalDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFormalDeclaration(ProcessJParser.FormalDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#formalDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFormalDeclaration(ProcessJParser.FormalDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#variableModifier}.
	 * @param ctx the parse tree
	 */
	void enterVariableModifier(ProcessJParser.VariableModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#variableModifier}.
	 * @param ctx the parse tree
	 */
	void exitVariableModifier(ProcessJParser.VariableModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#lastFormalDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterLastFormalDeclaration(ProcessJParser.LastFormalDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#lastFormalDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitLastFormalDeclaration(ProcessJParser.LastFormalDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(ProcessJParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(ProcessJParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentOperator(ProcessJParser.AssignmentOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentOperator(ProcessJParser.AssignmentOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#arrayElements}.
	 * @param ctx the parse tree
	 */
	void enterArrayElements(ProcessJParser.ArrayElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#arrayElements}.
	 * @param ctx the parse tree
	 */
	void exitArrayElements(ProcessJParser.ArrayElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#expressionWithBlock}.
	 * @param ctx the parse tree
	 */
	void enterExpressionWithBlock(ProcessJParser.ExpressionWithBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#expressionWithBlock}.
	 * @param ctx the parse tree
	 */
	void exitExpressionWithBlock(ProcessJParser.ExpressionWithBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#blockExpression}.
	 * @param ctx the parse tree
	 */
	void enterBlockExpression(ProcessJParser.BlockExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#blockExpression}.
	 * @param ctx the parse tree
	 */
	void exitBlockExpression(ProcessJParser.BlockExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(ProcessJParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(ProcessJParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#recordExpression}.
	 * @param ctx the parse tree
	 */
	void enterRecordExpression(ProcessJParser.RecordExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#recordExpression}.
	 * @param ctx the parse tree
	 */
	void exitRecordExpression(ProcessJParser.RecordExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#recordExpressionList}.
	 * @param ctx the parse tree
	 */
	void enterRecordExpressionList(ProcessJParser.RecordExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#recordExpressionList}.
	 * @param ctx the parse tree
	 */
	void exitRecordExpressionList(ProcessJParser.RecordExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#protocolExpression}.
	 * @param ctx the parse tree
	 */
	void enterProtocolExpression(ProcessJParser.ProtocolExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#protocolExpression}.
	 * @param ctx the parse tree
	 */
	void exitProtocolExpression(ProcessJParser.ProtocolExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#protocolExpressionList}.
	 * @param ctx the parse tree
	 */
	void enterProtocolExpressionList(ProcessJParser.ProtocolExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#protocolExpressionList}.
	 * @param ctx the parse tree
	 */
	void exitProtocolExpressionList(ProcessJParser.ProtocolExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#tagExpressionList}.
	 * @param ctx the parse tree
	 */
	void enterTagExpressionList(ProcessJParser.TagExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#tagExpressionList}.
	 * @param ctx the parse tree
	 */
	void exitTagExpressionList(ProcessJParser.TagExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#externalExpression}.
	 * @param ctx the parse tree
	 */
	void enterExternalExpression(ProcessJParser.ExternalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#externalExpression}.
	 * @param ctx the parse tree
	 */
	void exitExternalExpression(ProcessJParser.ExternalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#classExpression}.
	 * @param ctx the parse tree
	 */
	void enterClassExpression(ProcessJParser.ClassExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#classExpression}.
	 * @param ctx the parse tree
	 */
	void exitClassExpression(ProcessJParser.ClassExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#loopExpression}.
	 * @param ctx the parse tree
	 */
	void enterLoopExpression(ProcessJParser.LoopExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#loopExpression}.
	 * @param ctx the parse tree
	 */
	void exitLoopExpression(ProcessJParser.LoopExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#forLoopExpression}.
	 * @param ctx the parse tree
	 */
	void enterForLoopExpression(ProcessJParser.ForLoopExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#forLoopExpression}.
	 * @param ctx the parse tree
	 */
	void exitForLoopExpression(ProcessJParser.ForLoopExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInit(ProcessJParser.ForInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInit(ProcessJParser.ForInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void enterForUpdate(ProcessJParser.ForUpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void exitForUpdate(ProcessJParser.ForUpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#statementExpressionList}.
	 * @param ctx the parse tree
	 */
	void enterStatementExpressionList(ProcessJParser.StatementExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#statementExpressionList}.
	 * @param ctx the parse tree
	 */
	void exitStatementExpressionList(ProcessJParser.StatementExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#whileLoopExpression}.
	 * @param ctx the parse tree
	 */
	void enterWhileLoopExpression(ProcessJParser.WhileLoopExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#whileLoopExpression}.
	 * @param ctx the parse tree
	 */
	void exitWhileLoopExpression(ProcessJParser.WhileLoopExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#doWhileLoopExpression}.
	 * @param ctx the parse tree
	 */
	void enterDoWhileLoopExpression(ProcessJParser.DoWhileLoopExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#doWhileLoopExpression}.
	 * @param ctx the parse tree
	 */
	void exitDoWhileLoopExpression(ProcessJParser.DoWhileLoopExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#infiniteLoopExpression}.
	 * @param ctx the parse tree
	 */
	void enterInfiniteLoopExpression(ProcessJParser.InfiniteLoopExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#infiniteLoopExpression}.
	 * @param ctx the parse tree
	 */
	void exitInfiniteLoopExpression(ProcessJParser.InfiniteLoopExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#ifExpression}.
	 * @param ctx the parse tree
	 */
	void enterIfExpression(ProcessJParser.IfExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#ifExpression}.
	 * @param ctx the parse tree
	 */
	void exitIfExpression(ProcessJParser.IfExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#switchExpression}.
	 * @param ctx the parse tree
	 */
	void enterSwitchExpression(ProcessJParser.SwitchExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#switchExpression}.
	 * @param ctx the parse tree
	 */
	void exitSwitchExpression(ProcessJParser.SwitchExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#caseBlockExpression}.
	 * @param ctx the parse tree
	 */
	void enterCaseBlockExpression(ProcessJParser.CaseBlockExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#caseBlockExpression}.
	 * @param ctx the parse tree
	 */
	void exitCaseBlockExpression(ProcessJParser.CaseBlockExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#caseBlockGroupExpression}.
	 * @param ctx the parse tree
	 */
	void enterCaseBlockGroupExpression(ProcessJParser.CaseBlockGroupExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#caseBlockGroupExpression}.
	 * @param ctx the parse tree
	 */
	void exitCaseBlockGroupExpression(ProcessJParser.CaseBlockGroupExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#caseLabel}.
	 * @param ctx the parse tree
	 */
	void enterCaseLabel(ProcessJParser.CaseLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#caseLabel}.
	 * @param ctx the parse tree
	 */
	void exitCaseLabel(ProcessJParser.CaseLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#altExpression}.
	 * @param ctx the parse tree
	 */
	void enterAltExpression(ProcessJParser.AltExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#altExpression}.
	 * @param ctx the parse tree
	 */
	void exitAltExpression(ProcessJParser.AltExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#priAltExpression}.
	 * @param ctx the parse tree
	 */
	void enterPriAltExpression(ProcessJParser.PriAltExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#priAltExpression}.
	 * @param ctx the parse tree
	 */
	void exitPriAltExpression(ProcessJParser.PriAltExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#altBlodyExpression}.
	 * @param ctx the parse tree
	 */
	void enterAltBlodyExpression(ProcessJParser.AltBlodyExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#altBlodyExpression}.
	 * @param ctx the parse tree
	 */
	void exitAltBlodyExpression(ProcessJParser.AltBlodyExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanGuardExpression}
	 * labeled alternative in {@link ProcessJParser#altCase}.
	 * @param ctx the parse tree
	 */
	void enterBooleanGuardExpression(ProcessJParser.BooleanGuardExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanGuardExpression}
	 * labeled alternative in {@link ProcessJParser#altCase}.
	 * @param ctx the parse tree
	 */
	void exitBooleanGuardExpression(ProcessJParser.BooleanGuardExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GuardExpression_}
	 * labeled alternative in {@link ProcessJParser#altCase}.
	 * @param ctx the parse tree
	 */
	void enterGuardExpression_(ProcessJParser.GuardExpression_Context ctx);
	/**
	 * Exit a parse tree produced by the {@code GuardExpression_}
	 * labeled alternative in {@link ProcessJParser#altCase}.
	 * @param ctx the parse tree
	 */
	void exitGuardExpression_(ProcessJParser.GuardExpression_Context ctx);
	/**
	 * Enter a parse tree produced by the {@code AltExpression_}
	 * labeled alternative in {@link ProcessJParser#altCase}.
	 * @param ctx the parse tree
	 */
	void enterAltExpression_(ProcessJParser.AltExpression_Context ctx);
	/**
	 * Exit a parse tree produced by the {@code AltExpression_}
	 * labeled alternative in {@link ProcessJParser#altCase}.
	 * @param ctx the parse tree
	 */
	void exitAltExpression_(ProcessJParser.AltExpression_Context ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#guardExpression}.
	 * @param ctx the parse tree
	 */
	void enterGuardExpression(ProcessJParser.GuardExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#guardExpression}.
	 * @param ctx the parse tree
	 */
	void exitGuardExpression(ProcessJParser.GuardExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#repAltLoopExpression}.
	 * @param ctx the parse tree
	 */
	void enterRepAltLoopExpression(ProcessJParser.RepAltLoopExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#repAltLoopExpression}.
	 * @param ctx the parse tree
	 */
	void exitRepAltLoopExpression(ProcessJParser.RepAltLoopExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#parBlockExpression}.
	 * @param ctx the parse tree
	 */
	void enterParBlockExpression(ProcessJParser.ParBlockExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#parBlockExpression}.
	 * @param ctx the parse tree
	 */
	void exitParBlockExpression(ProcessJParser.ParBlockExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#barrierExpression}.
	 * @param ctx the parse tree
	 */
	void enterBarrierExpression(ProcessJParser.BarrierExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#barrierExpression}.
	 * @param ctx the parse tree
	 */
	void exitBarrierExpression(ProcessJParser.BarrierExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#externDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterExternDeclaration(ProcessJParser.ExternDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#externDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitExternDeclaration(ProcessJParser.ExternDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#externType}.
	 * @param ctx the parse tree
	 */
	void enterExternType(ProcessJParser.ExternTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#externType}.
	 * @param ctx the parse tree
	 */
	void exitExternType(ProcessJParser.ExternTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdentifierArguments}
	 * labeled alternative in {@link ProcessJParser#classType}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierArguments(ProcessJParser.IdentifierArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdentifierArguments}
	 * labeled alternative in {@link ProcessJParser#classType}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierArguments(ProcessJParser.IdentifierArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ClassDotIdentifierArguments}
	 * labeled alternative in {@link ProcessJParser#classType}.
	 * @param ctx the parse tree
	 */
	void enterClassDotIdentifierArguments(ProcessJParser.ClassDotIdentifierArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ClassDotIdentifierArguments}
	 * labeled alternative in {@link ProcessJParser#classType}.
	 * @param ctx the parse tree
	 */
	void exitClassDotIdentifierArguments(ProcessJParser.ClassDotIdentifierArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#typeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgumentsOrDiamond(ProcessJParser.TypeArgumentsOrDiamondContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#typeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgumentsOrDiamond(ProcessJParser.TypeArgumentsOrDiamondContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void enterTypeArguments(ProcessJParser.TypeArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void exitTypeArguments(ProcessJParser.TypeArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#typeArgumentList}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgumentList(ProcessJParser.TypeArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#typeArgumentList}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgumentList(ProcessJParser.TypeArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgument(ProcessJParser.TypeArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgument(ProcessJParser.TypeArgumentContext ctx);
}